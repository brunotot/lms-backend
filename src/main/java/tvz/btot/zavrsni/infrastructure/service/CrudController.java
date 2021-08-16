package tvz.btot.zavrsni.infrastructure.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CrudController<DTO, FORM, ID> {
    default ResponseEntity<List<DTO>> findAll() { throw new UnsupportedOperationException(); }
    default ResponseEntity<DTO> findById(@PathVariable ID id) { throw new UnsupportedOperationException(); }
    default ResponseEntity<DTO> create(@RequestBody FORM form) { throw new UnsupportedOperationException(); }
    default ResponseEntity<DTO> update(@PathVariable ID id, @RequestBody FORM form) { throw new UnsupportedOperationException(); }
    default ResponseEntity<FORM> getFormById(@PathVariable ID id) { throw new UnsupportedOperationException(); }
    default ResponseEntity<Void> delete(@PathVariable ID id) { throw new UnsupportedOperationException(); }
}
