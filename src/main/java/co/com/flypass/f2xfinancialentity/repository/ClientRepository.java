package co.com.flypass.f2xfinancialentity.repository;

import co.com.flypass.f2xfinancialentity.model.ClientModel;

import java.util.List;
import java.util.Optional;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 7:18 PM
 **/
public interface ClientRepository {
    Optional<ClientModel> getClient(String id);
    List<ClientModel> getAllClients();
    ClientModel createClient(ClientModel client);
    ClientModel updateClient(ClientModel client);
    void deleteClient(String id);
    boolean existClient(String id);

    boolean existClientByDocumentNumber(String identificationNumber);
}
