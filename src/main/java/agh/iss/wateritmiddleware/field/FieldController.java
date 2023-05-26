package agh.iss.wateritmiddleware.field;

import agh.iss.wateritmiddleware.field.model.FieldDto;
import agh.iss.wateritmiddleware.field.model.request.FieldRequest;
import agh.iss.wateritmiddleware.field.model.response.CropTypeResponse;
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

    @GetMapping("/crop-type")
    public ResponseEntity<CropTypeResponse> getCropTypes() {
        return ResponseEntity.ok(fieldService.getAllCropTypes());
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
}
