package service;
import jakarta.jws.WebService;
import dao.EmployerDao;
import model.Employer;

import java.util.List;

@WebService(endpointInterface = "service.EmployerService")
public class EmployerServiceImp implements EmployerService{
    private EmployerDao employerDao = new EmployerDao();

    @Override
    public void addEmployer(Employer employer) {
        this.employerDao.save(employer);
    }

    @Override
    public Employer getEmployer(int id) {
        return employerDao.getById(id);
    }

    @Override
    public List<Employer> getAllEmployers() {
        return employerDao.getAll();
    }

    @Override
    public void updateEmployer(Employer employer) {
        employerDao.update(employer);
    }

    @Override
    public void deleteEmployer(Employer employer) {
        employerDao.delete(employer);

    }
}
