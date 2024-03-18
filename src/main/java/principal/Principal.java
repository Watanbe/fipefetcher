package principal;

import com.fasterxml.jackson.core.type.TypeReference;
import model.*;
import service.ConsumeApi;
import service.ConvertData;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumeApi consumer = new ConsumeApi();
    private ConvertData conversor = new ConvertData();

    private List<Brand> brands = new ArrayList<>();
    private Models models;

    private Brand brand;

    private final String BASEURL = "https://parallelum.com.br/fipe/api/v1/";
    private String vehicleType = "";

    private void getPrices() {
        List<Year> years = new ArrayList<>();

        System.out.println("Informe o código do modelo para consultar preços!");
        var code = leitura.nextInt();
        leitura.nextLine();

        Optional<Model> model =  this.models.models().stream()
                .filter(m -> m.code() == code)
                .findFirst();

        if (model.isEmpty()) {
            System.out.println("Modelo não encontrado!");
            return;
        }

        var modelCode = model.get().code();
        var brandCode = this.brand.code();
        var json = this.consumer.getData(BASEURL + this.vehicleType + "/marcas/" + brandCode + "/modelos/" + modelCode + "/anos");
        years = this.conversor.getListData(json, Year.class);

        years.forEach(y -> {
            var carJson = this.consumer.getData(BASEURL + this.vehicleType + "/marcas/" + brandCode + "/modelos/" + modelCode + "/anos/" + y.code());
            Car car = this.conversor.getData(carJson, Car.class);
            System.out.println("Valor: " + car.value());
            System.out.println("Marca: " + car.brand());
            System.out.println("Modelo: " + car.model());
            System.out.println("Ano: " + car.year());
            System.out.println("Combustivel: " + car.gasType());
            System.out.println("Código fipe: " + car.fipeCode());
            System.out.println("Mês de referência: " + car.monthOfReference());
            System.out.println("");
        });

    }
    private void getModels() {
        System.out.println("Informe o código da marca para consultar modelos!");
        var code = leitura.nextInt();
        leitura.nextLine();

        Optional<Brand> brand =  this.brands.stream()
                .filter(b -> b.code() == code)
                .findFirst();

        if (brand.isEmpty()) {
            System.out.println("Código inválido!");
            return;
        }

        this.brand = brand.get();

        var brandCode = brand.get().code();
        var json = this.consumer.getData(BASEURL + this.vehicleType + "/marcas/" + brandCode + "/modelos");
        this.models = this.conversor.getData(json, Models.class);

        this.models.models().stream()
                .sorted(Comparator.comparing(Model::code))
                .forEach(m -> {
                    System.out.println("Código: " + m.code() + " - Nome: " + m.name());
                });
    }

    private void getBrands() {
        var json = this.consumer.getData(BASEURL + this.vehicleType + "/marcas");
        this.brands = conversor.getListData(json, Brand.class);
        this.brands.stream()
                .sorted(Comparator.comparing(Brand::code))
                .forEach(b -> {
                    System.out.println("Código: " + b.code() + " - Nome: " + b.name());
                });
    }
    private void vehicleType() {
        System.out.println("1 - Carros\n" +
                "2 - Motos\n" +
                "3 - Caminhões\n" +
                "0 - Voltar\n");

        var option = leitura.nextInt();
        leitura.nextLine();

        switch (option) {
            case 1:
                this.vehicleType = "carros";
                break;
            case 2:
                this.vehicleType = "motos";
                break;
            case 3:
                this.vehicleType = "caminhoes";
                break;
            default:
                return;
        }
    }

    public void mainMenu() {
        boolean inLoop = true;
        while (inLoop) {
            System.out.println("1 - Buscar veiculo\n" +
                    "0 - Sair\n");
            var option = leitura.nextInt();
            leitura.nextLine();

            switch (option) {
                case 1:
                    this.vehicleType();
                    this.getBrands();
                    this.getModels();
                    this.getPrices();
                    break;
                case 0:
                    inLoop = false;
                    break;
            }
        }

    }

}
