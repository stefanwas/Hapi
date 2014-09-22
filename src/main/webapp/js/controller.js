var invoiceServiceURL = 'http\\://localhost\\:8080/generator/rest/invoice/generate-pdf';
var invoiceGeneratorURL = 'rest/invoice/generate-pdf';

var hapi = angular.module('hapi',[ 'ngResource' ]);

hapi.controller('MainController', function($scope, invoiceService, downloadService) {

    $scope.deliveryDate = '09.2014'
    $scope.issueDate = '30.09.2014';

    $scope.items = [];

    $scope.totalNetto = 0;
    $scope.totalVAT = 0;
    $scope.totalBrutto = 0;

    $scope.issuerName = "";

    function createEmptyItem() {
        return {
            name : "",
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
        $scope.issuerName = "Wojciech Krzysztofik";
    }

//TODO finish it
    function createInvoice() {
        return {
            invoiceNumber : "FVAT201412X",
            sellDate : $scope.deliveryDate,
            issueDate : $scope.issueDate,
            sellerInfo : "ala ma kota",
            buyerInfo : "ola ma rower",
            paymentPeriod : "21 dni",
            paymentForm : "gotówka",
            issuer : $scope.issuerName
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
        $scope.updateTotal();
    };

    $scope.updateItem = function(item) {
        item.value = item.amount * item.price;
        item.vatValue = item.value * item.vatPercent / 100;
        item.totalValue = item.value + item.vatValue;

        $scope.updateTotal();
    };

    $scope.updateTotal = function() {
        $scope.totalNetto = _.reduce($scope.items, function(sum, item) {return sum + item.value}, 0);
        $scope.totalVAT = _.reduce($scope.items, function(sum, item) {return sum + item.vatValue}, 0);
        $scope.totalBrutto = _.reduce($scope.items, function(sum, item) {return sum + item.totalValue}, 0);
    };

    $scope.generateInvoice = function() {

        var invoiceData = {
            invoice : createInvoice()
        };

        downloadService.downloadFile(invoiceGeneratorURL, 'invoice=' + angular.toJson(createInvoice(), false));

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
            dataType: 'json',
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

