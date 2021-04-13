package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.PrescriptionMedicineBean;
import com.bean.ResponseBean;
import com.dao.PrescriptionMedicineDao;

@CrossOrigin
@RestController
public class PrescriptionMedicineController {
	
	@Autowired
	PrescriptionMedicineDao prescriptionmedicineDao;
	
//	@PostMapping("/addPrescriptionMedicine")
//    public ResponseBean<PrescriptionMedicineBean> addPrescriptionMedicine(@RequestBody PrescriptionMedicineBean prescriptionmedicineBean) {
//		prescriptionmedicineDao.addPrescriptionMedicine(prescriptionmedicineBean);
//        ResponseBean<PrescriptionMedicineBean> response = new ResponseBean<>();
//        response.setData(prescriptionmedicineBean);
//        response.setMsg("Prescription Medicine Added...!!");
//        response.setStatus(200);
//        return response;
//
//    }

    @GetMapping("/listPrescriptionMedicine")
    public ResponseBean<java.util.List<PrescriptionMedicineBean>> listPrescriptionMedicine() {
        ResponseBean<java.util.List<PrescriptionMedicineBean>> response = new ResponseBean<>();

        java.util.List<PrescriptionMedicineBean> prescriptionmedicineBean = prescriptionmedicineDao.listPrescriptionMedicine();
        response.setData(prescriptionmedicineBean);
        response.setMsg("Prescription Medicine List Display..!!!!");
        response.setStatus(201);
        return response;
    }
    
    @GetMapping("/getPrescriptionMedicine/{prescriptionmedicineid}")
    public ResponseBean<PrescriptionMedicineBean> getPrescriptionMedicine(@PathVariable("prescriptionmedicineid") int prescriptionmedicineid, PrescriptionMedicineBean prescriptionmedicineBean) {
        ResponseBean<PrescriptionMedicineBean> responseBean = new ResponseBean<>();
        prescriptionmedicineBean = prescriptionmedicineDao.getPrescriptionMedicineById(prescriptionmedicineid);
        responseBean.setData(prescriptionmedicineBean);
        responseBean.setMsg("Single Prescription Medicine Return");
        responseBean.setStatus(200);

        return responseBean;
    }

    @PutMapping("/updatePrescriptionMedicine")
    public ResponseBean<PrescriptionMedicineBean> updatePrescriptionMedicine(@RequestBody PrescriptionMedicineBean prescriptionmedicineBean) {
    	prescriptionmedicineDao.updatePrescriptionMedicine(prescriptionmedicineBean);
        ResponseBean<PrescriptionMedicineBean> response = new ResponseBean<>();
        response.setData(prescriptionmedicineBean);
        response.setMsg("Prescription Medicine Updated..!!");

        return response;
    }
    
    @DeleteMapping("/addPrescriptionMedicine/{prescriptionmedicineid}")
    public ResponseBean<PrescriptionMedicineBean> deletePrescriptionMedicine(@PathVariable("prescriptionmedicineid") int prescriptionmedicineid) {

        ResponseBean<PrescriptionMedicineBean> response = new ResponseBean<>();
        prescriptionmedicineDao.deletePrescriptionMedicine(prescriptionmedicineid);
        response.setMsg("Prescription Medicine Deleted..!!");
        response.setStatus(200);
        return response;
    }
}
