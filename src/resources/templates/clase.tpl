@Entity
@Table(name = "$entidad")
public class $NombreClase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "${idColumnName}")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "DatosLogisticosGenerator")
    @SequenceGenerator(name = "DatosLogisticosGenerator", sequenceName = "S_CP_DATOS_LOGISTICOS")
    private Long id;

    @Column(name = "LOG_SIMBOLIZADO")
    private Boolean simbolizado; 

    @JoinColumn(name = "LOG_UNIDAD_DIMENSION")
    @ManyToOne(fetch = FetchType.LAZY)
    private UnidadMedida unidadDimension;

    @Column(name = "LOG_PESO")
    private Integer peso;
    
}
