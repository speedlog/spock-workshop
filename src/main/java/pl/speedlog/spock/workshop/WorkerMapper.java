package pl.speedlog.spock.workshop;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
public class WorkerMapper {

    public WorkerDTO developerToWorkerDTO(Developer developer) {
        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.setName(developer.getPersonalInfo().getName());
        workerDTO.setSurname(developer.getPersonalInfo().getSurname());
        workerDTO.setPhone(developer.getPersonalInfo().getPhone());
        workerDTO.setAdditionalInfo("Languages: " + developer.getLanguages());
        return workerDTO;
    }

    public WorkerDTO adminToWorkerDTO(Admin admin) {
        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.setName(admin.getPersonalInfo().getName());
        workerDTO.setSurname(admin.getPersonalInfo().getSurname());
        workerDTO.setPhone(admin.getPersonalInfo().getPhone());
        workerDTO.setAdditionalInfo("Systems: " + admin.getSystems());
        return workerDTO;
    }
}

@RequiredArgsConstructor
@Getter
class PersonalInfo {
    final String name;
    final String surname;
    final String phone;
}

@RequiredArgsConstructor
@Getter
class Developer {
    final String languages;
    final PersonalInfo personalInfo;
}

@RequiredArgsConstructor
@Getter
class Admin {
    final String systems;
    final PersonalInfo personalInfo;
}

@Getter
@Setter
class WorkerDTO {
    String name;
    String surname;
    String phone;
    String additionalInfo;
}
