package fooddelivery.infra;

import fooddelivery.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping(value="/stores")
@Transactional
public class StoreController {

    @Autowired
    StoreRepository storeRepository;

    @RequestMapping(
        value = "stores/{id}/acceptorder",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Store acceptOrder(
        @PathVariable(value = "id") Long id,
        @RequestBody AcceptOrderCommand acceptOrderCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /store/acceptOrder  called #####");
        Optional<Store> optionalStore = storeRepository.findById(id);

        optionalStore.orElseThrow(() -> new Exception("No Entity Found"));
        Store store = optionalStore.get();
        store.acceptOrder(acceptOrderCommand);

        storeRepository.save(store);
        return store;
    }

    @RequestMapping(
        value = "stores/{id}/rejectorder",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Store rejectOrder(
        @PathVariable(value = "id") Long id,
        @RequestBody RejectOrderCommand rejectOrderCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /store/rejectOrder  called #####");
        Optional<Store> optionalStore = storeRepository.findById(id);

        optionalStore.orElseThrow(() -> new Exception("No Entity Found"));
        Store store = optionalStore.get();
        store.rejectOrder(rejectOrderCommand);

        storeRepository.save(store);
        return store;
    }

    @RequestMapping(
        value = "stores/{id}/startcook",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Store startCook(
        @PathVariable(value = "id") Long id,
        @RequestBody StartCookCommand startCookCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /store/startCook  called #####");
        Optional<Store> optionalStore = storeRepository.findById(id);

        optionalStore.orElseThrow(() -> new Exception("No Entity Found"));
        Store store = optionalStore.get();
        store.startCook(startCookCommand);

        storeRepository.save(store);
        return store;
    }

    @RequestMapping(
        value = "stores/{id}/finishcook",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Store finishCook(
        @PathVariable(value = "id") Long id,
        @RequestBody FinishCookCommand finishCookCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /store/finishCook  called #####");
        Optional<Store> optionalStore = storeRepository.findById(id);

        optionalStore.orElseThrow(() -> new Exception("No Entity Found"));
        Store store = optionalStore.get();
        store.finishCook(finishCookCommand);

        storeRepository.save(store);
        return store;
    }
}
