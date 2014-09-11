var hapi = angular.module('hapi',[]);

hapi.controller('MainController', function($scope) {

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
        $scope.totalNetto = _.reduce($scope.items, function(sum, item) {return sum + item.value},0);
        $scope.totalVAT = _.reduce($scope.items, function(sum, item) {return sum + item.vatValue},0);
        $scope.totalBrutto = _.reduce($scope.items, function(sum, item) {return sum + item.totalValue},0);
    };

    init();


});
