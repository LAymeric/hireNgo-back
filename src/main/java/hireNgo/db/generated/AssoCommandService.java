package hireNgo.db.generated;

import javax.annotation.Generated;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.querydsl.sql.Column;

/**
 * AssoCommandService is a Querydsl bean type
 */
@Generated("com.coreoz.plume.db.querydsl.generation.IdBeanSerializer")
public class AssoCommandService extends com.coreoz.plume.db.querydsl.crud.CrudEntityQuerydsl {

    @JsonSerialize(using=com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Column("id")
    private Long id;

    @JsonSerialize(using=com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Column("id_command")
    private Long idCommand;

    @JsonSerialize(using=com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Column("id_service")
    private Long idService;

    @JsonSerialize(using=com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Column("id_user_accompanist")
    private Long idUserAccompanist;

    @Column("quantity")
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCommand() {
        return idCommand;
    }

    public void setIdCommand(Long idCommand) {
        this.idCommand = idCommand;
    }

    public Long getIdService() {
        return idService;
    }

    public void setIdService(Long idService) {
        this.idService = idService;
    }

    public Long getIdUserAccompanist() {
        return idUserAccompanist;
    }

    public void setIdUserAccompanist(Long idUserAccompanist) {
        this.idUserAccompanist = idUserAccompanist;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (id == null) {
            return super.equals(o);
        }
        if (!(o instanceof AssoCommandService)) {
            return false;
        }
        AssoCommandService obj = (AssoCommandService) o;
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
        return "AssoCommandService#" + id;
    }

}

