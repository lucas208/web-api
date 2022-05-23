package rn.sead.gov.model.generic;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Where;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntity extends RepresentationModel<AbstractEntity> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "status", columnDefinition = "boolean DEFAULT 'true'")
    private Boolean status = true;

}
