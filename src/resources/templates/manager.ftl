@BusinessProxy
public interface ${className}Manager {

    public static final String MANAGER_NAME = "${upperClassName}_MANAGER_NAME";

    @WebBusinessOperation(value = "${upperClassName}_GET_WEB", convertToClass = ${className}ServiceDto.class)
    public abstract ${className} get(Long id);

    @BusinessOperation("${upperClassName}_FIND")
    public abstract List<${className}> find(${className}ServiceDto dto);

    @WebBusinessOperation(value = "${upperClassName}_FIND_PAGE", convertToClass = ${className}ServiceDto.class)
    public abstract Page findPage(${className}ServiceDto dto);

    @BusinessOperation("${upperClassName}_UPDATE")
    public abstract void update(${className}ServiceDto dto);

    @WebBusinessOperation("${upperClassName}_UPDATE_WEB")
    public abstract ${className}ServiceDto updateWeb(${className}ServiceDto dto) throws ValidationException, FrameworkException;

   
    @BusinessOperation("${upperClassName}_DELETE")
    public abstract void delete(Long id);
    
    @BusinessOperation("${upperClassName}_SAVE_OR_UPDATE")
    public abstract void saveOrUpdate(${className} ${lowerClassName});

    
}