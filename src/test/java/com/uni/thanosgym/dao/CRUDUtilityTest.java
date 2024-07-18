package com.uni.thanosgym.dao;

import com.uni.thanosgym.utils.StringUtils;
import com.itextpdf.styledxmlparser.jsoup.internal.StringUtil;
import com.uni.thanosgym.model.Admin;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.model.Utility;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CRUDUtilityTest {

    CRUDUtilidad crudUtility = CRUDUtilidad.getInstance();
    CRUDAdministrador crudAdministrador = CRUDAdministrador.getInstance();

    @Test
    public void mainTest() {
        assertTrue(true);
        Admin adminForVerify = new Admin.Builder().build();
        if (crudAdministrador.getQuantity() != 0) {
            adminForVerify = crudAdministrador.getAdminMasterOnlyForTesting().getData();
        }
        
        // create administrador
        Admin admin = new Admin.Builder()
                .setFullName("Admin test")
                .setEmail("test.testmaster@gmail")
                .setPhone("986327221")
                .setUsername("testusername")
                .setPassword("testusername")
                .setRol(Admin.Rol.MASTER)
                .setPhotoUrl("photo")
                .build();
        admin.setId(crudAdministrador.create(admin, adminForVerify).getId());

        // create utility
        Utility utility = new Utility.Builder()
                .setAdmin(admin)
                .setNombre("Test Utility")
                .setPeso(100)
                .setCantidad(10)
                .setPhotoUrl("photo_url")
                .build();
        Response<Utility> response = crudUtility.create(utility);
        assertTrue(response.isSuccess());

        // update
        int id = response.getId();
        String nameEdited = "Test Utility edited";
        utility.setNombre(nameEdited);
        utility.setId(id);
        Response<Utility> response2 = crudUtility.update(utility);
        assertTrue(response2.isSuccess());

        // delete utility
        Response<Utility> response3 = crudUtility.delete(id);
        assertTrue(response3.isSuccess());

        // delete admin
        crudAdministrador.deleteOnlyForTesting(admin.getId());
    }
}
