package luismis.mx.ris.Pay.Model;

import jakarta.persistence.*;
import luismis.mx.ris.Pay_Details.Model.Pay_Details;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "pay")
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id", nullable = false, updatable = false)
    private Long payId; // ID del pago

    @Column(name = "user_id", nullable = false)
    private Long userId; // ID del usuario (relación con User)

    @Column(name = "total_amount", nullable = false)
    private Long totalAmount; // Monto total del pago

    @Column(name = "payment_date", nullable = false)
    private Timestamp paymentDate; // Fecha del pago

    @Column(name = "status", nullable = false, length = 20)
    private String status; // Estado: pending, completed, failed, deleted

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt; // Fecha de creación

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt; // Fecha de última actualización

    @Column(name = "deleted_at")
    private Timestamp deletedAt; // Fecha de eliminación (opcional)

    @OneToMany(mappedBy = "pay", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pay_Details> payDetails;


    public Pay() {

    }

    public Pay(Long payId, Long userId, Long totalAmount, Timestamp paymentDate, String status, Timestamp createdAt, Timestamp updatedAt, Timestamp deletedAt) {
        this.payId = payId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.paymentDate = paymentDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public Pay(Long userId, Long totalAmount, Timestamp paymentDate, String status, Timestamp createdAt, Timestamp updatedAt, Timestamp deletedAt) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.paymentDate = paymentDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
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
