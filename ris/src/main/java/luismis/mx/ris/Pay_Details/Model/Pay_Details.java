package luismis.mx.ris.Pay_Details.Model;

import jakarta.persistence.*;
import luismis.mx.ris.Pay.Model.Pay;
import luismis.mx.ris.Product.Model.Product;
import luismis.mx.ris.Product.Model.ProductDTO;

import java.sql.Timestamp;

@Entity
@Table(name = "pay_details")
public class Pay_Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_details_id", nullable = false, updatable = false)
    private Long payDetailsId; // ID del detalle de pago

    @Column(name = "quantity", nullable = false)
    private int quantity; // Cantidad del producto

    @Column(name = "subtotal", nullable = false)
    private Long subtotal; // Subtotal para este producto

    @Column(name = "status", nullable = false, length = 20)
    private String status; // Estado: active, inactive, deleted

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt; // Fecha de creación

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt; // Fecha de última actualización

    @Column(name = "deleted_at")
    private Timestamp deletedAt; // Fecha de eliminación (opcional)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pay_id", nullable = false)
    private Pay pay;


    public Pay_Details() {
    }

    public Pay_Details(Long payDetailsId, Pay pay, Product product, int quantity, Long subtotal, String status, Timestamp createdAt, Timestamp updatedAt, Timestamp deletedAt) {
        this.payDetailsId = payDetailsId;
        this.pay = pay;
        this.product = product;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public Pay_Details(Pay pay, Product product, int quantity, Long subtotal, String status, Timestamp createdAt, Timestamp updatedAt, Timestamp deletedAt) {
        this.pay = pay;
        this.product = product;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public Long getPayDetailsId() {
        return payDetailsId;
    }

    public void setPayDetailsId(Long payDetailsId) {
        this.payDetailsId = payDetailsId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Pay getPay() {
        return pay;
    }

    public void setPay(Pay pay) {
        this.pay = pay;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Long subtotal) {
        this.subtotal = subtotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }
}
