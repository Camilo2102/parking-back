package co.com.parking.utils;

import co.com.parking.controller.GeneralController;
import co.com.parking.models.GeneralModel;
import co.com.parking.repository.GeneralRepository;
import co.com.parking.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanGenerator {

    private final BeanLocator beanLocator;

    @Autowired
    public BeanGenerator(BeanLocator beanLocator){
        this.beanLocator = beanLocator;
    }
    public GeneralController<?> getRequiredController(String repository) throws ClassNotFoundException {
        String repositoryName = repository + "Repository";
        GeneralRepository<?> repositorySelected = beanLocator.getBeanByString(repositoryName, GeneralRepository.class);
        GeneralService<? extends GeneralModel> service = new GeneralService<>(repositorySelected);

        return new GeneralController<>(service);
    }

    public Class<?>  getClassFromPath (String path) throws ClassNotFoundException {
        Class<?> className = beanLocator.getClassByName(StringUtil.firstUpperLetter(path));
        return className.asSubclass(GeneralModel.class);
    }
}
