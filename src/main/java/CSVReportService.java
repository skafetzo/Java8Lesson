import model.Person;
import model.Transaction;
import repositories.TransactionRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.util.stream.Collectors.toMap;

public class CSVReportService {

    private final PersonsService personsService;
    private final TransactionRepository transactionRepository;

    public CSVReportService(PersonsService personsService, TransactionRepository transactionRepository) {
        this.personsService = personsService;
        this.transactionRepository = transactionRepository;
    }

    /**
     * Retrieve the average consumption (transaction amount) per @{@link Person}'s distinct roles during the last month
     *
     * Note that roles are just tags that each person is assigned. ie 'student', 'gamer', 'athlete', 'parent'
     * a Person may have multiple roles or none.
     *
     * @return data in csv file format,
     *         where the first line depict the roles
     *         and the second line the average consumption per role
     * ie: (formatted example -- the actual output should be just comma separated)
     * |student, gamer, parent|
     * |10.50  , 20.10, 0     |
     */
    public String  getAverageConsumptionPerRoleDuringTheLastMonth() {

        String header = " ";
        String body = " " ;

        List<Transaction> transactions = transactionRepository.getTransactions();

     //   Map<String, Long> sumTransactionPerEmail =


                transactions.stream()
                .filter(this::isInLastMonth)
                .map(e -> {
                    Map<Optional<Person>, Long> t = new HashMap<>();
                    t.put(personsService.getPersonByEmailAddress(e.getEmailAddress()), e.getAmount());
                            return t;
                }).filter(map -> map.entrySet().isEmpty());


     return header + "\n" + body;

    }


      private boolean isInLastMonth(Transaction transaction) {
        LocalDateTime lastMonth = LocalDateTime.now().minus(30, ChronoUnit.DAYS);
        return transaction.getDate().isAfter(lastMonth);
    }




}
