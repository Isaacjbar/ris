package luismis.mx.ris.Pay.Controller;


import luismis.mx.ris.Pay.Model.Pay;
import luismis.mx.ris.Pay.Model.PayDTO;
import luismis.mx.ris.Pay.Model.PayRepository;
import luismis.mx.ris.Util.Response.Message;
import luismis.mx.ris.Util.Enum.TypesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Transactional
@Service
public class PayService {

    private static final Logger logger = LoggerFactory.getLogger(PayService.class);

    private final PayRepository payRepository;

    @Autowired
    public PayService(PayRepository payRepository) {
        this.payRepository = payRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Pay> payments = payRepository.findAll();
        logger.info("La búsqueda de pagos se realizó correctamente");
        return new ResponseEntity<>(new Message(payments, "Listado de pagos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(PayDTO dto) {
        if (dto.getStatus().length() > 20) {
            return new ResponseEntity<>(new Message("El estado excede el número de caracteres permitidos", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Pay pay = new Pay();
        pay.setUserId(dto.getUserId());
        pay.setTotalAmount(dto.getTotalAmount());
        pay.setStatus(dto.getStatus());
        pay.setPaymentDate(new Timestamp(System.currentTimeMillis()));
        pay.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        pay.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        payRepository.save(pay);
        logger.info("El pago fue registrado correctamente");
        return new ResponseEntity<>(new Message(pay, "El pago fue registrado correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(PayDTO dto) {
        return payRepository.findById(dto.getPayId().intValue()) // Conversión de Long a Integer
                .map(pay -> {
                    if (dto.getUserId() != null) {
                        pay.setUserId(dto.getUserId());
                    }
                    if (dto.getTotalAmount() != null) {
                        pay.setTotalAmount(dto.getTotalAmount());
                    }
                    if (dto.getStatus() != null && dto.getStatus().length() <= 20) {
                        pay.setStatus(dto.getStatus());
                    }
                    pay.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

                    payRepository.save(pay);
                    logger.info("El pago fue actualizado correctamente");
                    return new ResponseEntity<>(new Message(pay, "El pago fue actualizado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(new Message("El pago no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND));
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(PayDTO dto) {
        return payRepository.findById(dto.getPayId().intValue()) // Conversión de Long a Integer
                .map(pay -> {
                    pay.setStatus(pay.getStatus().equals("active") ? "inactive" : "active");
                    pay.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

                    payRepository.save(pay);
                    logger.info("El estado del pago fue actualizado correctamente");
                    return new ResponseEntity<>(new Message(pay, "El estado del pago fue actualizado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(new Message("El pago no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND));
    }
}
