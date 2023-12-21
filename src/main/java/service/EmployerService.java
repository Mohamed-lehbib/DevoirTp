package service;
import jakarta.jws.WebService;
import model.Employer;
import java.util.List;

@WebService
public interface EmployerService {
    void addEmployer(Employer employer);
    Employer getEmployer(int id);
    List<Employer> getAllEmployers();
    void updateEmployer(Employer employer);
    void deleteEmployer(Employer employer);
}

