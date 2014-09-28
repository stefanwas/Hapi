//hapi.factory('filterDataResource', [ '$resource', function($resource) {
//    return $resource(filterServiceURL, { filterName: '@filterName' },
//        {
//            get : {
//                method : 'GET',
//                params : {query : '@query', maxResults : '@maxResults'},
//                isArray : false
//            },
//            getAll : {
//                method : 'GET',
//                isArray : false
//            }
//        });
//} ]);

hapi.service('currencyService', function() {

    // 0 <= intValue <= 999
    function getAsText(intValue) {
        var result = '';

        var hundred = Math.floor(intValue/100);
        var ten = Math.floor((intValue - 100 * hundred) / 10);
        var one = intValue % 10;

        if (hundred > 0) {
            switch (hundred) {
                case 1 : result += 'sto'; break;
                case 2 : result += 'dwieście'; break;
                case 3 : result += 'trzysta'; break;
                case 4 : result += 'czterysta'; break;
                case 5 : result += 'pięćset'; break;
                case 6 : result += 'sześćset'; break;
                case 7 : result += 'siedemset'; break;
                case 8 : result += 'osiemset'; break;
                case 9 : result += 'dziewięćset'; break;
            }
        }

        if (ten >= 2) {
            result += ' ';
            switch (ten) {
                case 2 : result += 'dwadzieścia'; break;
                case 3 : result += 'trzydzieści'; break;
                case 4 : result += 'czterdzieści'; break;
                case 5 : result += 'pięćdziesiąt'; break;
                case 6 : result += 'sześćdziesiąt'; break;
                case 7 : result += 'siedemdziesiąt'; break;
                case 8 : result += 'osiemdziesiąt'; break;
                case 9 : result += 'dziewięćdziesiąt'; break;
            }
        } else if (ten == 1) {
            result += ' ';
            switch (one) {
                case 0 : result += 'dziesięć'; break;
                case 1 : result += 'jedenaście'; break;
                case 2 : result += 'dwanaście'; break;
                case 3 : result += 'trzynaście'; break;
                case 4 : result += 'czternaście'; break;
                case 5 : result += 'piętnaście'; break;
                case 6 : result += 'szesnaście'; break;
                case 7 : result += 'siedemnaście'; break;
                case 8 : result += 'osiemnaście'; break;
                case 9 : result += 'dziewiętnaście'; break;
            }
        }

        if (one > 0 && ten != 1) {
            result += ' ';
            switch (one) {
                case 1 : result += 'jeden'; break;
                case 2 : result += 'dwa'; break;
                case 3 : result += 'trzy'; break;
                case 4 : result += 'cztery'; break;
                case 5 : result += 'pięć'; break;
                case 6 : result += 'sześć'; break;
                case 7 : result += 'siedem'; break;
                case 8 : result += 'osiem'; break;
                case 9 : result += 'dziewięć'; break;
            }
        }

        return result;
    }

    function getMillionForm(intValue) {
        var hundred = Math.floor(intValue/100);
        var tens = Math.floor((intValue - 100 * hundred) / 10);
        var ones = intValue % 10;

        if (intValue == 1) {
            return 'milion';
        }

        if (tens == 1) {
            return 'milionów';
        }

        if (ones >= 2 && ones <= 4) {
            return 'miliony';
        }

        return 'milionów';
    }

    function getThousandForm(intValue) {
        var hundred = Math.floor(intValue/100);
        var tens = Math.floor((intValue - 100 * hundred) / 10);
        var ones = intValue % 10;

        if (intValue == 1) {
            return 'tysiąc';
        }

        if (tens == 1) {
            return 'tysięcy';
        }

        if (ones >= 2 && ones <= 4) {
            return 'tysiące';
        }

        return 'tysięcy';
    }

    function getMillionsAsText(millions) {
        var millionText = getAsText(millions);
        var millionForm = getMillionForm(millions);
        return millionText + ' ' + millionForm;
    }

    function getThousandsAsText(thousands) {
        var thousandText = getAsText(thousands);
        var thousandForm = getThousandForm(thousands);
        return thousandText + ' ' + thousandForm;
    }
    function getUnitsAsText(units) {
        var unitText = getAsText(units);
        return unitText;
    }

    var service = {};

    service.getTextDescription = function(value) {

        var maxValue = 999999999;
        var minValue = 0;

        if (!value) {
            return '-';
        }

        if (value > maxValue) {
            return "to już jest grubsza sumka..."; // out of range message
        }

        if (value < minValue) {
            return "mniej niż zero..."; // out of range message
        }

        var intValue = Math.floor(value);
        if (intValue == 0) {
            return 'zero';
        }


        var millions = Math.floor(intValue / 1000000);
        var thousands = Math.floor((intValue - millions * 1000000)/1000);
        var units = intValue % 1000;

        var result = '';

        result += (millions != 0) ? getMillionsAsText(millions) : '';
        result += (thousands != 0) ? ((result.length > 0) ? ' ' : '') + getThousandsAsText(thousands) : '';
        result += (units != 0) ? ((result.length > 0) ? ' ' : '') + getUnitsAsText(units) : '';

        return result;
    };

    return service;
});