package com.tabeldata.bootcamp.web.controller;

import com.tabeldata.bootcamp.web.dao.ExampleDao;
import com.tabeldata.bootcamp.web.dao.KategoryDao;
import com.tabeldata.bootcamp.web.model.Example;
import com.tabeldata.bootcamp.web.model.Kategori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/latihan")
public class Controller {

    @Autowired
    private KategoryDao kdao;

    @GetMapping("/list")
    public List<Kategori> list() {
        return kdao.list();
    }

    @GetMapping("/findByIdk/{id}")
    public ResponseEntity<?> findByIdk(@PathVariable Integer id) {
        try {
            Kategori data = this.kdao.findById(id);
            return ResponseEntity.ok(data);
        } catch (EmptyResultDataAccessException erdae) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/showk")
    public Kategori showData(
            @RequestParam(name = "name", required = true) String name,
            @RequestParam(name = "des", required = true) String description,
            Integer category_id,
            Integer department_id)
    {
        Kategori data = new Kategori();
        data.setCategory_id(category_id);
        data.setDepartment_id(department_id);
        data.setName(name);
        data.setDescription(description);
        return data;
    }

    @PostMapping(value = "/inputk")
    public ResponseEntity<?> inputData(@RequestBody @Valid Kategori data) {
        try {
            this.kdao.insert(data);
            return ResponseEntity.ok().build();
        } catch (DuplicateKeyException dke) {
            dke.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("Duplicate data");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Server Internal Error");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        this.kdao.delete(id);
        return ResponseEntity.ok().build();
    }
}