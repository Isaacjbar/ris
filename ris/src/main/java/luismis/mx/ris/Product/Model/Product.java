package luismis.mx.ris.Product.Model;

import jakarta.persistence.*;
import luismis.mx.ris.Pay_Details.Model.Pay_Details;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, updatable = false)
    private Long productId; // ID del producto

    @Column(name = "name", nullable = false, length = 100)
    private String name; // Nombre del producto

    @Column(name = "description", length = 500)
    private String description; // Descripción del producto

    @Column(name = "price", nullable = false)
    private Long price; // Precio del producto

    @Column(name = "stock", nullable = false)
    private int stock; // Cantidad disponible

    @Column(name = "status", nullable = false, length = 20)
    private String status; // Estado: active, inactive, deleted

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt; // Fecha de creación

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt; // Fecha de última actualización

    @Column(name = "deleted_at")
    private Timestamp deletedAt; // Fecha de eliminación (opcional)

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pay_Details> payDetails;


    public Product() {

    }

    public Product(Long productId, String name, String description, Long price, int stock, String status, Timestamp createdAt, Timestamp updatedAt, Timestamp deletedAt) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public Product(String name, String description, Long price, int stock, String status, Timestamp createdAt, Timestamp updatedAt, Timestamp deletedAt) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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
