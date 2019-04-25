package ua.donskykh.mybookstore.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code = "MYBOOK" + UUID.randomUUID().toString();

    @Column(name = "title")
    @NotBlank(message = "Please Enter Book title")
    private String title;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "date_publish")
    @NotBlank(message = "Please Enter Date of book publish!")
    private String dateOfPublish;

    @Column(name = "description")
    @JsonIgnore
    @NotBlank(message = "Please Enter Book description")
    private String description;

    @Column(name = "price")
    @Min(value = 1, message = "Price at least one (1)")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "category_id")
    @JsonIgnore
    private Long categoryId;

    @Column(name = "supplier_id")
    @JsonIgnore
    private long supplierId;

    @Column(name = "purchases")
    private int purchases;

    @Transient
    private MultipartFile file;

    public Book() {
        this.code = "MYBOOK" + UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDateOfPublish() {
        return dateOfPublish;
    }

    public void setDateOfPublish(String dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public int getPurchases() {
        return purchases;
    }

    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Book[" + "id=" + id + ", code='" + code + ", title='" + title +
                ", authorName=" + authorName + ", dateOfPublish='" + dateOfPublish + ", description='" + description +
                ", price=" + price + ", quantity=" + quantity + ", active=" + active + ", categoryId=" + categoryId +
                ", supplierId=" + supplierId + ", purchases=" + purchases + ", file=" + file + ']';
    }
}
