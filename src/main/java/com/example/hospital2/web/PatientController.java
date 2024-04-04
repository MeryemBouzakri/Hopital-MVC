package com.example.hospital2.web;

import com.example.hospital2.entities.Patient;
import com.example.hospital2.repository.PatientRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0")int p,
                        @RequestParam(name = "size",defaultValue = "4") int s,
                        @RequestParam(name = "keyword",defaultValue = "") String kw
    )
    {
        Page<Patient> pagePatients=patientRepository.findByNomContains (kw,PageRequest.of(p,s));
      model.addAttribute("listPatient",pagePatients.getContent());
      model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
      model.addAttribute("currentPage",p);
      model.addAttribute("keyword",kw);
        return "patients";
    }
    @GetMapping("/delete")
    public String delete (Long id,String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/index?"+page+"$keyword="+keyword;
    }
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/formPatients")
    public String formPatient(Model model) {
        model.addAttribute("patient", new Patient()); // Ajoute un patient vide pour le binding du formulaire
        return "formPatients";
    }
    @PostMapping("/save")
    public String save( Model model , @Valid Patient patient , BindingResult bindingResult, @RequestParam(defaultValue = "") String keyword , @RequestParam(defaultValue = "0") int page){
        if (bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }






}
