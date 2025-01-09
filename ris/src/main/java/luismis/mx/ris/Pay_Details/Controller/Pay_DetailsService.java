package luismis.mx.ris.Pay_Details.Controller;

import luismis.mx.ris.Pay.Model.Pay;
import luismis.mx.ris.Pay.Model.PayRepository;
import luismis.mx.ris.Pay_Details.Model.Pay_Details;
import luismis.mx.ris.Pay_Details.Model.Pay_DetailsDTO;
import luismis.mx.ris.Pay_Details.Model.Pay_DetailsRepository;
import luismis.mx.ris.Product.Model.Product;
import luismis.mx.ris.Product.Model.ProductRepository;
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
public class Pay_DetailsService {

    private static final Logger logger = LoggerFactory.getLogger(Pay_DetailsService.class);

    private final Pay_DetailsRepository payDetailsRepository;
    private final PayRepository payRepository;
    private final ProductRepository productRepository; ;

    @Autowired
    public Pay_DetailsService(Pay_DetailsRepository payDetailsRepository, PayRepository payRepository, ProductRepository productRepository) {
        this.payDetailsRepository = payDetailsRepository;
        this.payRepository = payRepository;
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Pay_Details> payDetails = payDetailsRepository.findAll();
        logger.info("La búsqueda de detalles de pago ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(payDetails, "Listado de detalles de pago", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(Pay_DetailsDTO dto) {
        if (dto.getStatus().length() > 20) {
            return new ResponseEntity<>(new Message("El estado excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        // Buscar Pay y Product por sus IDs
        Pay pay = payRepository.findById(dto.getPayId().intValue())
                .orElseThrow(() -> new IllegalArgumentException("El ID del pago no existe"));
        Product product = productRepository.findById(dto.getProductId().intValue())
                .orElseThrow(() -> new IllegalArgumentException("El ID del producto no existe"));

        // Crear la entidad Pay_Details
        Pay_Details payDetails = new Pay_Details();
        payDetails.setPay(pay);
        payDetails.setProduct(product);
        payDetails.setQuantity(dto.getQuantity());
        payDetails.setSubtotal(dto.getSubtotal());
        payDetails.setStatus("active");
        payDetails.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        payDetailsRepository.save(payDetails);
        return new ResponseEntity<>(new Message(payDetails, "El detalle de pago se registró correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }


    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(Pay_DetailsDTO dto) {
        return payDetailsRepository.findById(dto.getPayDetailsId().intValue())
                .map(payDetails -> {
                    if (dto.getPayId() != null) {
                        Pay pay = payRepository.findById(dto.getPayId().intValue())
                                .orElseThrow(() -> new IllegalArgumentException("El ID del pago no existe"));
                        payDetails.setPay(pay);
                    }
                    if (dto.getProductId() != null) {
                        Product product = productRepository.findById(dto.getProductId().intValue())
                                .orElseThrow(() -> new IllegalArgumentException("El ID del producto no existe"));
                        payDetails.setProduct(product);
                    }
                    if (dto.getQuantity() > 0) {
                        payDetails.setQuantity(dto.getQuantity());
                    }
                    if (dto.getSubtotal() != null) {
                        payDetails.setSubtotal(dto.getSubtotal());
                    }
                    if (dto.getStatus() != null) {
                        if (dto.getStatus().length() > 20) {
                            return new ResponseEntity<>(new Message("El estado excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
                        }
                        payDetails.setStatus(dto.getStatus());
                    }
                    payDetails.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    payDetailsRepository.save(payDetails);
                    return new ResponseEntity<>(new Message(payDetails, "El detalle de pago se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(new Message("El detalle de pago no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND));
    }


    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(Pay_DetailsDTO dto) {
        return payDetailsRepository.findById(dto.getPayDetailsId().intValue())
                .map(payDetails -> {
                    payDetails.setStatus(payDetails.getStatus().equals("active") ? "inactive" : "active");
                    payDetails.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    payDetailsRepository.save(payDetails);
                    logger.info("La actualización del estado del detalle de pago ha sido realizada correctamente");
                    return new ResponseEntity<>(new Message(payDetails, "El estado del detalle de pago se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(new Message("El detalle de pago no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND));
    }
}
