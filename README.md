Project using Cucumber > 4.

A branch exists for each major version of Cucumber.

The master branch uses the cucumber junit platform engine, this was used as a test but it should NOT be used.

Quick explanations on the Parameters.feature:

@Community

Will assert the following operation: 

(DSNAME > (DS_current_stock + TC_estimated_production))

@Premium

CPG_CUF_product_value_is_five : (bool) assertTrue on the value

IT_CUF_warehouse_name : (str) should be equal to "WH1948"

Will assert the following operation: 

((DS_current_stock * 5) / 3 ) > (DS_renting_cost + TS_CUF_maintenance_cost)