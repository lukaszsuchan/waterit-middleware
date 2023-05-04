package agh.iss.wateritmiddleware.field;

import agh.iss.wateritmiddleware.field.model.FieldDto;
import agh.iss.wateritmiddleware.field.model.ZoneDto;
import agh.iss.wateritmiddleware.field.model.request.FieldRequest;
import agh.iss.wateritmiddleware.field.model.request.ZoneRequest;
import agh.iss.wateritmiddleware.utils.UriBuilder;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fields")
public class FieldController {

    private final FieldService fieldService;
    private final UriBuilder uriBuilder;

    @Operation(summary = "create field and assigned it to user")
    @PostMapping
    public ResponseEntity<Void> createFiled(@RequestBody FieldRequest request) {
        final var filedId = fieldService.addField(request);
        var locationUri = uriBuilder.requestUriWithId(filedId);

        return ResponseEntity.created(locationUri).build();
    }

    @Operation(summary = "return all user's fields")
    @GetMapping
    public ResponseEntity<List<FieldDto>> getAllUserFields() {
        return ResponseEntity.ok(fieldService.getAllUserFields());
    }

    @Operation(summary = "return user's field by id")
    @GetMapping("{id}")
    public ResponseEntity<FieldDto> getField(@PathVariable Long id) {
        return ResponseEntity.ok(fieldService.getFieldById(id));
    }

    @Operation(summary = "create zone on specified field", description = "create zone on specified field and split rest of the field")
    @PostMapping("{id}/zones")
    public ResponseEntity<Void> createZone(@PathVariable Long id, @RequestBody ZoneRequest request) {
        final var zoneId = fieldService.addZoneAndSplitField(id, request);
        var locationUri = uriBuilder.requestUriWithId(zoneId);

        return ResponseEntity.created(locationUri).build();
    }

    @Operation(summary = "return all field's zones")
    @GetMapping("{id}/zones")
    public ResponseEntity<List<ZoneDto>> getAllUserFields(@PathVariable Long id) {
        return ResponseEntity.ok(fieldService.getAllFieldZone(id));
    }
}
