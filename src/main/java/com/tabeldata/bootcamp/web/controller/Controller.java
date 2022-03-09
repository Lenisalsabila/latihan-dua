package com.tabeldata.bootcamp.web.controller;

import com.tabeldata.bootcamp.web.dao.KategoryDao;
import com.tabeldata.bootcamp.web.model.Kategori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/latihan")
public class Controller {

    @Autowired
    private KategoryDao kdao;

    @GetMapping("/list")
    public List<Kategori> list() {
        return kdao.list();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findByIdk(@PathVariable Integer id) {
        try {
            Kategori data = this.kdao.findById(id);
            return ResponseEntity.ok(data);
        } catch (EmptyResultDataAccessException erdae) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/show")
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


//    @PostMapping(value = "/input")
//    public ResponseEntity<Map<String, Object>>
//    insertData(@RequestBody @Valid Kategori datak, BindingResult result) {
//        Map<String, Object> hasil = new HashMap<>();
//        hasil.put("id", kdao.insertData(datak));
//        hasil.put("status", "Simpan berhasil");
//        return ResponseEntity.ok(hasil);
//    }
    @PostMapping(value = "/input")
    public ResponseEntity<?> insertData(@Valid @RequestBody  Kategori datak, BindingResult result) {
        Map<String, Object> hasil = new HashMap<>();

        if(result.hasErrors()){
            hasil.put("status", result.getFieldErrors()  );
            return ResponseEntity.badRequest().body(hasil);
        }else {
            hasil.put("id", kdao.insertData(datak));
            hasil.put("status", "Simpan berhasil");
            return ResponseEntity.ok(hasil);
        }
//        try {
//            this.kdao.insertData(datak);
//            return ResponseEntity.ok().build();
//        } catch (DuplicateKeyException dke) {
//            dke.printStackTrace();
//            return ResponseEntity.badRequest()
//                    .body("Duplicate data");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return ResponseEntity.internalServerError()
//                    .body("Server Internal Error");
//        }
    }

//    @PutMapping(value = "/update/{id}")
//    public Kategori update(@PathVariable(value = "categogy_id") Integer category_id, @RequestParam("department_id") Integer department_id, @RequestParam("name") String name, @RequestParam("desripction") String desripction){
//        Kategori category = new Kategori();
//        category.setCategory_id(category_id);
//        category.setDepartment_id(department_id);
//        category.setName(name);
//        category.setDescription(desripction);
//        return category;
//    }
        @PostMapping("/update")
        public ResponseEntity<Map<String, Object>>
        updateCategory(@RequestBody Kategori datak) {
            Map<String, Object> hasil = new HashMap<>();
            kdao.updateCategory(datak);
            hasil.put("id", 0);
            hasil.put("status", "Update Berhasil");
            return ResponseEntity.ok().build();

        }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        this.kdao.delete(id);
        return ResponseEntity.ok().build();
    }

}