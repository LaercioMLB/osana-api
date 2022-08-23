package br.com.uniamerica.Osana.controller;

import br.com.uniamerica.Osana.dto.OSDTO;
import br.com.uniamerica.Osana.dto.input.NewOSDTO;
import br.com.uniamerica.Osana.service.OSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
//
@RestController
@RequestMapping("/os")
@RequiredArgsConstructor
public class OSController {

    private final OSService osService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OSDTO> listItems() {
        return osService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OSDTO addItem(@Valid @RequestBody NewOSDTO newOSDTO) {
        return osService.save(newOSDTO);
    }

    @GetMapping(path = {"/{id}"})
    @ResponseStatus(HttpStatus.FOUND)
    public OSDTO findById(@PathVariable long id){
        return osService.findById(id);
    }

    @PutMapping(path = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public OSDTO update(@PathVariable long id, @Valid @RequestBody NewOSDTO newOSDTO) {
        return osService.update(id, newOSDTO);
    }

    @DeleteMapping(path ={"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable long id) {
        return osService.deleteById(id);
    }
}
