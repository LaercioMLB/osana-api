package br.com.uniamerica.osanabackend.controllers;

import br.com.uniamerica.osanabackend.entities.OS;
import br.com.uniamerica.osanabackend.repositories.OSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class OSController {
    @Autowired
    OSRepository osRepository;
    @GetMapping("/oss")
    public ResponseEntity<List<OS>> getAllOSs(@RequestParam(required = false) Date dateOS){
        try {
            List<OS> oss = new ArrayList<OS>();
            if (dateOS == null)
                osRepository.findAll().forEach(oss::add);
            else
                osRepository.findByDateOS(dateOS).forEach(oss::add);
            if (oss.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(oss, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/oss/{id}")
    public ResponseEntity<OS> getOSById(@PathVariable("id") long id){
        Optional<OS> osData = osRepository.findById(id);
        if(osData.isPresent()){
            return new ResponseEntity<>(osData.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/oss")
    public ResponseEntity<OS> createOS(@RequestBody OS os){
        try {
            OS _os = osRepository.save(new OS(os.getMotive(), os.getObs(), os.getDevolution(), os.getDateOS()));
            return new ResponseEntity<>(_os, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/oss/{id}")
    public ResponseEntity<OS> updateOS(@PathVariable("id") long id, @RequestBody OS os) {
        Optional<OS> osData = osRepository.findById(id);
        if (osData.isPresent()) {
            OS _os = osData.get();
            _os.setMotive(os.getMotive());
            _os.setObs(os.getObs());
            _os.setDevolution(os.getDevolution());
            _os.setDateOS(os.getDateOS());
            return new ResponseEntity<>(osRepository.save(_os), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/oss/{id}")
    public ResponseEntity<HttpStatus> deleteOS(@PathVariable("id") long id) {
        try {
            osRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/oss")
    public ResponseEntity<HttpStatus> deleteAllOS() {
        try {
            osRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
