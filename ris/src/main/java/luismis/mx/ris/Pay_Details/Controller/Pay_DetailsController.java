package luismis.mx.ris.Pay_Details.Controller;

import luismis.mx.ris.Pay_Details.Model.Pay_DetailsDTO;
import luismis.mx.ris.Pay_Details.Controller.Pay_DetailsService;
import luismis.mx.ris.Util.Response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pay-details")
public class Pay_DetailsController {

    private final Pay_DetailsService payDetailsService;

    @Autowired
    public Pay_DetailsController(Pay_DetailsService payDetailsService) {
        this.payDetailsService = payDetailsService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllPayDetails() {
        return payDetailsService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Message> savePayDetails(@Validated(Pay_DetailsDTO.Registrar.class) @RequestBody Pay_DetailsDTO dto) {
        return payDetailsService.save(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updatePayDetails(@Validated(Pay_DetailsDTO.Modificar.class) @RequestBody Pay_DetailsDTO dto) {
        return payDetailsService.update(dto);
    }

    @PutMapping("/change-status")
    public ResponseEntity<Message> changePayDetailsStatus(@Validated(Pay_DetailsDTO.CambiarEstado.class) @RequestBody Pay_DetailsDTO dto) {
        return payDetailsService.changeStatus(dto);
    }
}
