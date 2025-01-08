package luismis.mx.ris.Pay.Controller;

import luismis.mx.ris.Pay.Model.PayDTO;
import luismis.mx.ris.Util.Response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pay")
public class PayController {

    private final PayService payService;

    @Autowired
    private PayController(PayService payService) {
        this.payService = payService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllPayments() {
        return payService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Message> savePayment(@Validated(PayDTO.Registrar.class) @RequestBody PayDTO dto) {
        return payService.save(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updatePayment(@Validated(PayDTO.Modificar.class) @RequestBody PayDTO dto) {
        return payService.update(dto);
    }

    @PutMapping("/change-status")
    public ResponseEntity<Message> changePaymentStatus(@Validated(PayDTO.CambiarEstado.class) @RequestBody PayDTO dto) {
        return payService.changeStatus(dto);
    }
}
