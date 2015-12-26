package pl.edu.pw.ee.cosplay.server.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Michał on 2015-12-26.
 */
public class StandardGetController<T> {

    CrudRepository<T, Long> someDAO;

    protected StandardGetController(CrudRepository<T, Long> someDAO){
        this.someDAO = someDAO;
    }

    protected ResponseEntity<byte[]> getEntities(){
        try {
            final ArrayList<T> entitiesList = new ArrayList<>();
            someDAO.findAll().forEach(entity -> entitiesList.add(entity));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
            out.writeObject(entitiesList);
            out.close();
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return new ResponseEntity<>(bytes, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT); //słaba absługa bledow ;-)
        }
    }

    protected ResponseEntity<byte[]> getEntity(Long entityId){
        try {
            T entity = someDAO.findOne(entityId);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
            out.writeObject(entity);
            out.close();
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return new ResponseEntity<>(bytes, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT); //słaba absługa bledow ;-)
        }
    }
}
