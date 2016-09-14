angular.module('app').controller('PeopleController', ['PeopleService', function PeopleController(PeopleService)
		{
	var ctrl = this;
	PeopleService.getAllPeople().then(function(result){
	ctrl.people=result.data;	
	});
}]);