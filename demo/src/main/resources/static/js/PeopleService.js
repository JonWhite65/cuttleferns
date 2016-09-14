angular.module('app').service('PeopleService', function($http){
	this.getAllPeople = function() { return $http.get('/people') };
});