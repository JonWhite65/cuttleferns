angular.module('app').service('PersonService', ['$http', function($http) {
	this.getPersonData = function(id) { return $http.get('/people/' + id) };
	this.patchPersonData=function(person){return $http.patch('/people/'+person.id,person)};
}]);