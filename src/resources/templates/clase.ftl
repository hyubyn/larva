@Entity
@Table(name = "${tableName}")
public class ${className} implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "${className}Generator")
    @SequenceGenerator(name = "${className}Generator", sequenceName = "S_${tableName}")
    private Long id;    
    
    <#list atributos as att>
    @Column(name = "${att.tableFieldName}")
    private ${att.fieldClass.simpleName} ${att.fieldName};
    
    </#list>
}
