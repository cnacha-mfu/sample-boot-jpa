package th.mfu.domain.dto;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import th.mfu.domain.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEmployeeFromDto(EmployeeDTO dto, @MappingTarget Employee entity);

}
