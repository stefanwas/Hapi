hapi.factory('filterDataResource', [ '$resource', function($resource) {
    return $resource(filterServiceURL, { filterName: '@filterName' },
        {
            get : {
                method : 'GET',
                params : {query : '@query', maxResults : '@maxResults'},
                isArray : false
            },
            getAll : {
                method : 'GET',
                isArray : false
            }
        });
} ]);