package com.khanh.todo_app.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.khanh.todo_app.dto.TaskRequestDto;
import com.khanh.todo_app.dto.TaskResponseDto;
import com.khanh.todo_app.model.Task;

// MapStruct will automatically generate the implementation of this interface
@Mapper (componentModel = "spring") // componetModel = "spring" to let Spring
// manage the mapper as a bean
public interface TaskMapper {

  // Convert TaskRequestDto to Task entity (Use for POST)
  @Mapping(target = "id", ignore = true) // ignore id field when mapping
  Task toEntity(TaskRequestDto dto);

  // Convert Entity to Response DTO (Use for GET/POST/PUT response)
  TaskResponseDto toDto(Task entity);

  // Convert List<Entity> to List<Dto> (Use for GET all)
  List<TaskResponseDto> toDtoList(List<Task> entities);

  // Convert and update Entity (Use for PUT)
  // MapStruct only overrides the fields that are present in the DTO
  @Mapping(target = "id", ignore = true) // ignore id field when mapping
  void updateEntityFromDto(TaskRequestDto dto, @MappingTarget  Task entity);

  // if you need more custom mappings, you can define them here
  TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
}
