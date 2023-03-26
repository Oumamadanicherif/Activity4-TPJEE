package ma.enset.jpatp4.entities;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @NotEmpty
    @Size(min= 4 , max=50)
    private String nom;
    @Temporal(TemporalType.DATE)

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissanec;
    private boolean malade;
    @DecimalMin("100")
    private int score;
    // @Data automatiquement ajout getters and setters
    //@NoArgsConstructor constructeur sans parametre
    //@AllArgsConstrctor constructeur avec parametre

}
