@Service(${className}Manager.MANAGER_NAME)
public class ${className}ManagerDefault implements ${className}Manager {

    @Autowired
    private ${className}Dao ${lowerCamelCaseClassName}Dao; 
    public ${className} get(Long id){
    	return ${lowerCamelCaseClassName}Dao.get(id);
    }

    public List<${className}> find(${className}ServiceDto dto){
    	return ${lowerCamelCaseClassName}Dao.find(dto);
    }

    public Page findPage(${className}ServiceDto dto){
    	return ${lowerCamelCaseClassName}Dao.findPage(dto);
    }

    public void update(${className}ServiceDto dto){
    	${lowerCamelCaseClassName}Dao.update(dto);
    }

    public ${className}ServiceDto updateWeb(${className}ServiceDto dto) throws ValidationException, FrameworkException{
    	return ${lowerCamelCaseClassName}Dao.update(dto);
    }
   
    public void delete(Long id){
    	${lowerCamelCaseClassName}Dao.deleteById(id);
    }
    
    public void saveOrUpdate(${className} ${lowerCamelCaseClassName}){
    	${lowerCamelCaseClassName}Dao.saveOrUpdate(${lowerClassName});
    }
}