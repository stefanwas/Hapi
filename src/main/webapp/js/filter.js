hapi.filter('asMoney', function() {

    return function(input, query) {
        if (input) {
            return input.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,");
        } else {
            return 0;
        }
    };

//        function formatMoney(n, currency) {
//            return n.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1.");
//        }
});