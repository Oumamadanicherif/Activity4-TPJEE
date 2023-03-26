package ma.enset.jpatp4.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.enset.jpatp4.entities.Patient;
import ma.enset.jpatp4.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller @AllArgsConstructor
public class PatientController {
    // injection des dependences, pas recommande
    private PatientRepository patientRepository;

//    public PatientController(PatientRepository patientRepository) {
//        this.patientRepository = patientRepository;
//    } ----> @AllArgsCoonstructor
    //Vue
    // Acces
    @GetMapping(path = "/index")
    public String patient(Model model,
                          @RequestParam(name = "page",defaultValue = "0")int page,
                          @RequestParam(name = "size",defaultValue = "5") int size,
                          @RequestParam(name = "keyword",defaultValue = "") String keyword ){
        Page<Patient> Pagepatients= patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listpatients", Pagepatients.getContent());
        model.addAttribute("pages",new int [Pagepatients.getTotalPages()]);
        model.addAttribute("keyword",keyword);
        model.addAttribute("currentPage",page);
    return "patients";

    }
    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping(path = "/")
    public String home(){
        return "redirect:/index";
    }
    @GetMapping(path = "/patients")
    @ResponseBody
    public List<Patient> patientList(){
        return patientRepository.findAll();

    }
    @GetMapping("/formPatients")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
     return "formPatients";
    }
    @PostMapping(path = "/save")
    public  String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                        @RequestParam(defaultValue = "0")    int page ,
                        @RequestParam(defaultValue = "")  String keyword){
    if(bindingResult.hasErrors())  return "formPatients";
    patientRepository.save(patient);
    return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping (path = "/editPatient")
    public  String editPatient(Model model, Long id, String keyword, int page){
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient == null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";
    }



}
