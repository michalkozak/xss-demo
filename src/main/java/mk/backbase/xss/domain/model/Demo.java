package mk.backbase.xss.domain.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "demo")
@Data
public class Demo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "demo_id_generator")
    @SequenceGenerator(name = "demo_id_generator", sequenceName = "demo_id_seq", allocationSize = 1, initialValue = 100)
    private long id;

    @Basic
    @Column(nullable = false, length = 256)
    private String name;

    @Basic
    @Column(nullable = true, length = 1024)
    private String description;

}
