var app = angular.module('MyBookStoreApp', []);

app.controller('BookController', function($http) {
    var me = this;
    me.mvBooks = [];
    me.mpBooks = [];

    me.fetchProducts = function() {


        $http.get('/mybookstore/json/data/mv/books')
            .then(function(response) {
                me.mvBooks = response.data;
            });


        $http.get('/mybookstore/json/data/mp/books')
            .then(function(response) {
                me.mpBooks = response.data;
            });
    }
});