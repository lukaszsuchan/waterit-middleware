package agh.iss.wateritmiddleware.device;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeviceController {

    @GetMapping
    public ResponseEntity<Void> getDevice() {
        return ResponseEntity.ok(null);
    }
}
