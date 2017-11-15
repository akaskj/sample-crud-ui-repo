angular.module('vehicles', [])
.controller('vehicleController', function($scope, $http) {

    $scope.vehicles = [];
    $scope.form = {
        Id : -1,
        Year : "",
        Make : "",
        Model : ""
    };

    $scope.submitVehicle = function() {

        console.log($scope.form.Id);

        var method = "";
        var url = "";
        if ($scope.form.Id == -1) {
            method = "POST";
            url = 'http://localhost:8080/vehicles';
        } else {
            method = "PUT";
            url = 'http://localhost:8080/vehicles/' + $scope.form.id;
        }

        $http({
            method : method,
            url : 'http://localhost:8080/vehicles',
            data : angular.toJson($scope.form),
            headers : {
                'Content-Type' : 'application/json'
            }
        }).then(_success, _error);
    };

    $scope.editVehicle = function(vehicle) {
        $scope.form.Id = vehicle.Id
        $scope.form.Year = vehicle.Year;
        $scope.form.Make = vehicle.Make;
        $scope.form.Model = vehicle.Model;
    };


    $scope.deleteVehicle = function(vehicle) {
        $http({
            method : 'DELETE',
            url : 'vehicles/' + vehicle.Id
        }).then(_success, _error);
    };


    $http.get('http://localhost:8080/vehicles').
    then(function(response) {
        $scope.res = response.data;
    });


    $scope.showVehicleDetailEdit = function() {

        $http.get('http://localhost:8080/vehicles/' + $scope.vid).
        then(function(response) {

               console.log($scope.vid);
            $scope.vres = response.data;

            $scope.id = $scope.vid
            $scope.year = $scope.vres.Year
            $scope.make = $scope.vres.Make
            $scope.model = $scope.vres.Model

        }, function() {
            $scope.id = ""
            $scope.year = ""
            $scope.make = ""
            $scope.model = ""
        });
    }

    function _refreshPageData() {
        $http.get('http://localhost:8080/vehicles').
        then(function(response) {
            $scope.res = response.data;
        });
    }

    function _success(response) {
        alert("Success!")
        _refreshPageData();
        _clearForm()
    }

    function _error(response) {
        alert("Error!")
    }

    function _clearForm() {
        $scope.form.Id = -1;
        $scope.form.Year = "";
        $scope.form.Make = "";
        $scope.form.Model = "";
    };

});

