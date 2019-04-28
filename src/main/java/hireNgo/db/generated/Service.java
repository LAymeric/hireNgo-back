package hireNgo.db.generated;

import javax.annotation.Generated;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.querydsl.sql.Column;

/**
 * Service is a Querydsl bean type
 */
@Generated("com.coreoz.plume.db.querydsl.generation.IdBeanSerializer")
public class Service extends com.coreoz.plume.db.querydsl.crud.CrudEntityQuerydsl {

    @JsonSerialize(using=com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Column("id")
    private Long id;

    @Column("isAccompanist")
    private Boolean isAccompanist;

    @Column("name")
    private String name;

    @Column("price")
    private String price;

    @Column("productLibelle")
    private String productLibelle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsAccompanist() {
        return isAccompanist;
    }

    public void setIsAccompanist(Boolean isAccompanist) {
        this.isAccompanist = isAccompanist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductLibelle() {
        return productLibelle;
    }

    public void setProductLibelle(String productLibelle) {
        this.productLibelle = productLibelle;
    }

    @Override
    public boolean equals(Object o) {
        if (id == null) {
            return super.equals(o);
        }
        if (!(o instanceof Service)) {
            return false;
        }
        Service obj = (Service) o;
        return id.equals(obj.id);
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return super.hashCode();
        }
        final int prime = 31;
        int result = 1;
        result = prime * result + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Service#" + id;
    }

}

