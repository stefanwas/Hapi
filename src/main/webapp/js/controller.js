var invoiceServiceURL = 'http\\://localhost\\:8080/generator/rest/invoice/generate-pdf';
var invoiceGeneratorURL = 'rest/invoice/generate-pdf';

var hapi = angular.module('hapi',[ 'ngResource' ]);

hapi.controller('MainController', function($scope, invoiceService, downloadService, currencyService) {

    $scope.invoiceNumber = '';
    $scope.deliveryDate = getCurrentDate();
    $scope.issueDate = getCurrentDate();

    $scope.sellerInfo = '';
    $scope.sellerInfoPlaceholder = ' Przykładowy Sprzedawca\n' +
        ' ul. Marszałkowska 1/1A\n 000-01 Warszawa\n NIP: 768-800-44-52\n\n Nr konta:' +
        ' 12 1510 1000 2000 3000 4000 0001\n Bank: PKO BP';
    $scope.issuerName = '';
    $scope.paymentPeriod = '';
    $scope.paymentForm = '';

    $scope.items = [];

    function getCurrentDate() {
        var now = new Date();

        return now.getDate() + '.' + (now.getMonth() + 1) + '.' + now.getFullYear();
    }

    function createEmptyItem() {
        return {
            name : '',
            amount : 1,
            price : 0,
            value : 0,
            vatPercent : 23,
            vatValue : 0,
            totalValue : 0
        };
    }

    function init() {
        $scope.addNewItem();
    }

    function extractDecimalPart(value) {
        var total = $scope.totalBrutto.toFixed(2);
        return Math.floor((total - Math.floor(total)) * 100);
    }

    function createInvoice() {
        return {
            invoiceNumber : $scope.invoiceNumber,
            sellDate : $scope.deliveryDate,
            issueDate : $scope.issueDate,
            sellerInfo : $scope.sellerInfo,
            buyerInfo : $scope.buyerInfo,
            paymentPeriod : $scope.paymentPeriod,
            paymentForm : $scope.paymentForm,
            issuer : $scope.issuerName,
            items : $scope.items
        };
    }

    $scope.addNewItem = function() {
        $scope.items.push(createEmptyItem());
    };

    $scope.removeItem = function() {
        $scope.items.splice($scope.items.length-1, 1);
        $scope.updateTotal();
    };

    $scope.removeAllItems = function() {
        $scope.items = [];
        $scope.addNewItem();
        $scope.updateTotal();
    };

    $scope.clearAll = function() {
            $scope.invoiceNumber = '';
            $scope.deliveryDate = '';
            $scope.issueDate = '';
            $scope.sellerInfo = '';
            $scope.buyerInfo = '';
            $scope.paymentPeriod = '';
            $scope.paymentForm = '';
            $scope.issuerName = '';

            $scope.removeAllItems();
    };

    $scope.updateItem = function(item) {

        if (item.amount == null || item.price == null) {
            item.value = 0;
        } else {
            var price = item.price.replace(".","").replace(",", ".");
            item.value = item.amount * price;
        }

        item.vatValue = (item.vatPercent == null) ? 0 : item.value * item.vatPercent / 100;
        item.totalValue = item.value + item.vatValue;

        $scope.updateTotal();
    };

    $scope.updateTotal = function() {
        var totalNetto = _.reduce($scope.items, function(sum, item) {return sum + item.value}, 0);
        $scope.totalNetto = totalNetto;

        var totalVAT = _.reduce($scope.items, function(sum, item) {return sum + item.vatValue}, 0);
        $scope.totalVAT = totalVAT;

        var totalBrutto = _.reduce($scope.items, function(sum, item) {return sum + item.totalValue}, 0);
        $scope.totalBrutto = totalBrutto;


        $scope.totalBruttoAsText = currencyService.getTextDescription(totalBrutto);
    };

    $scope.generateInvoice = function() {

        var invoice = createInvoice();
        console.log(">>>INVOICE: " + angular.toJson(invoice, true));

        downloadService.downloadFile(invoiceGeneratorURL, 'invoice=' + angular.toJson(invoice, false));

//        invoiceService.generate(invoiceData,
//            function (result) {
//                console.log(">>>RES: " + angular.toJson(result, true));
//            },
//            function (error) {
//                console.log(">>>ERR: " + angular.toJson(result, true));
//            }
//        );
    };

    init();

});

hapi.service('downloadService', function() {

    var service = {};

    service.downloadFile = function(link, data) {
        var payload = {
            httpMethod: 'POST',
//            dataType: 'json',
            contentType:"application/json",
            data: data,
//            headers: {
//                'Accept' : "*/*",
//                'Content-Type': "application/json; charset=utf-8"
//            },
            accept: '*/*'
        };

        $.fileDownload(link, payload)
        .done(function() {
            // nothing to do
        })
        .fail(function() {
            // TODO show alert
        });
    };

    return service;
});

hapi.factory('invoiceService', [ '$resource', function($resource) {

        var  requestParams = {
            method : 'POST',
            headers : {
                'Content-Type' : 'application/json'
            },
            isArray : false
        };

        return $resource(invoiceServiceURL, {}, {generate : requestParams});
}]);

