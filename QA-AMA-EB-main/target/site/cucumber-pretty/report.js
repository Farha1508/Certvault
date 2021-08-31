$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("AMA.feature");
formatter.feature({
  "line": 1,
  "name": "AMA-EB Login",
  "description": "",
  "id": "ama-eb-login",
  "keyword": "Feature"
});
formatter.before({
  "duration": 2692414700,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Login, Add and Details Policy, KPI\u0027s, Grid, Import Policies and Logout",
  "description": "",
  "id": "ama-eb-login;login,-add-and-details-policy,-kpi\u0027s,-grid,-import-policies-and-logout",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I navigate to login page of EB app",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I enter login credentials",
  "rows": [
    {
      "cells": [
        "email",
        "password"
      ],
      "line": 7
    },
    {
      "cells": [
        "renuka.gundu@patracorp.net",
        "Mar@2019"
      ],
      "line": 8
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I click on Sign In button",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "Pagination value",
  "rows": [
    {
      "cells": [
        "pagevalue"
      ],
      "line": 11
    },
    {
      "cells": [
        "20"
      ],
      "line": 12
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "Get the KPI Count",
  "keyword": "And "
});
formatter.step({
  "comments": [
    {
      "line": 15,
      "value": "##################--------Add Policy-------####################"
    }
  ],
  "line": 16,
  "name": "Click on Add Policy",
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "Enter Add Policy Details",
  "rows": [
    {
      "cells": [
        "AccountManager",
        "Company",
        "Status",
        "BusinessType",
        "NewExisting",
        "Date",
        "EmployerGroup",
        "CoverageType",
        "PriorCarrier",
        "PriorPlanName",
        "ExpiringPremium",
        "ExpiringCommission",
        "RenewalNewCarrier",
        "RenewalNewPlanName",
        "RenewalNewPremium",
        "RenewalNewCommission",
        "LeadSource"
      ],
      "line": 18
    },
    {
      "cells": [
        "renuka Gundu",
        "ONI Risk Partners - PB",
        "Untouched",
        "Prospect",
        "New",
        "16/04/2019",
        "Tes1",
        "Life",
        "Aetna",
        "Test",
        "12",
        "12",
        "Aetna",
        "Test",
        "12",
        "12",
        "Agency"
      ],
      "line": 19
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 20,
  "name": "Click on Submit button",
  "keyword": "And "
});
formatter.step({
  "comments": [
    {
      "line": 22,
      "value": "##################--------Details Policy-------####################"
    }
  ],
  "line": 24,
  "name": "I select a record under Policies tab",
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "Navigate to Policy Details section",
  "rows": [
    {
      "cells": [
        "AccountManagerDetails",
        "CompanyDetails",
        "StatusDetails",
        "BusinessTypeDetails",
        "NewExistingDetails",
        "DateDetails",
        "EmployerGroupDetails",
        "CoverageTypeDetails",
        "PriorCarrierDetails",
        "ExpiringPremiumDetails",
        "ExpiringCommissionDetails",
        "RenewalNewCarrierDetails",
        "RenewalNewPremiumDetails",
        "RenewalNewCommissionDetails",
        "LeadSourceDetails"
      ],
      "line": 26
    },
    {
      "cells": [
        "Prathima M",
        "ONI Risk Partners - PB",
        "In Progress",
        "Renewal",
        "Existing",
        "20/04/2019",
        "xyz",
        "Vision",
        "Chubb",
        "",
        "13",
        "Chubb",
        "13",
        "13",
        "Existing Customer"
      ],
      "line": 27
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "comments": [
    {
      "line": 29,
      "value": "###########---------Questions,Notes,History Tabs----###########"
    }
  ],
  "line": 31,
  "name": "Click on Add Note Button",
  "keyword": "Then "
});
formatter.step({
  "line": 32,
  "name": "Input values in the Note pop-up",
  "rows": [
    {
      "cells": [
        "Title",
        "Description"
      ],
      "line": 33
    },
    {
      "cells": [
        "Note",
        "Note"
      ],
      "line": 34
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 35,
  "name": "Input values in the Questions tab",
  "rows": [
    {
      "cells": [
        "Question1",
        "Question2",
        "Question3",
        "Question4"
      ],
      "line": 36
    },
    {
      "cells": [
        "Yes",
        "Yes",
        "Yes",
        "Yes"
      ],
      "line": 37
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 38,
  "name": "Click on Notes Tab",
  "keyword": "And "
});
formatter.step({
  "line": 39,
  "name": "Click on History tab",
  "keyword": "And "
});
formatter.step({
  "line": 40,
  "name": "Click on Submit and Close button",
  "keyword": "And "
});
formatter.step({
  "comments": [
    {
      "line": 42,
      "value": "################--------Grid Search Filters--------##############"
    }
  ],
  "line": 44,
  "name": "Search with all the filters in the grid",
  "rows": [
    {
      "cells": [
        "AccountManagerDetails",
        "CompanyDetails",
        "StatusDetails",
        "BusinessTypeDetails",
        "EmployerGroupDetails",
        "CoverageTypeDetails",
        "ExpiringPremiumDetails",
        "ExpiringCommissionDetails",
        "PriorCarrierDetails"
      ],
      "line": 45
    },
    {
      "cells": [
        "Prathima M",
        "ONI Risk Partners - PB",
        "In Progress",
        "Renewal",
        "xyz",
        "Vision",
        "13",
        "13",
        "Aetna"
      ],
      "line": 46
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "comments": [
    {
      "line": 48,
      "value": "#######################---------KPI\u0027s-----------###############"
    }
  ],
  "line": 50,
  "name": "Click on My Prospects KPI",
  "keyword": "And "
});
formatter.step({
  "line": 51,
  "name": "I search a record with the Employer Group",
  "rows": [
    {
      "cells": [
        "employergroupsearch"
      ],
      "line": 52
    },
    {
      "cells": [
        "tes1"
      ],
      "line": 53
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 54,
  "name": "I select a record under Policies tab",
  "keyword": "And "
});
formatter.step({
  "line": 55,
  "name": "I move the record to Renewal",
  "rows": [
    {
      "cells": [
        "MovRec"
      ],
      "line": 56
    },
    {
      "cells": [
        "Renewal"
      ],
      "line": 57
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 58,
  "name": "Click on Submit and Close button",
  "keyword": "Then "
});
formatter.step({
  "line": 59,
  "name": "Get the My Prospects KPI Count",
  "keyword": "And "
});
formatter.step({
  "line": 60,
  "name": "Click on My Renewals KPI",
  "keyword": "And "
});
formatter.step({
  "line": 61,
  "name": "I search a record with the Employer Group",
  "rows": [
    {
      "cells": [
        "employergroupsearch"
      ],
      "line": 62
    },
    {
      "cells": [
        "tes1"
      ],
      "line": 63
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 64,
  "name": "I select a record under Policies tab",
  "keyword": "And "
});
formatter.step({
  "line": 65,
  "name": "I move the record to New Business",
  "rows": [
    {
      "cells": [
        "MovRec1"
      ],
      "line": 66
    },
    {
      "cells": [
        "New"
      ],
      "line": 67
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 68,
  "name": "Click on Submit and Close button",
  "keyword": "Then "
});
formatter.step({
  "line": 69,
  "name": "Get the My Renewals KPI Count",
  "keyword": "And "
});
formatter.step({
  "line": 70,
  "name": "Click On My New Business KPI",
  "keyword": "And "
});
formatter.step({
  "line": 71,
  "name": "I search a record with the Employer Group",
  "rows": [
    {
      "cells": [
        "employergroupsearch"
      ],
      "line": 72
    },
    {
      "cells": [
        "tes1"
      ],
      "line": 73
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 74,
  "name": "I select a record under Policies tab",
  "keyword": "And "
});
formatter.step({
  "line": 75,
  "name": "Move the record to Lost Business",
  "rows": [
    {
      "cells": [
        "MovRec2"
      ],
      "line": 76
    },
    {
      "cells": [
        "Lost"
      ],
      "line": 77
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 78,
  "name": "Click on Submit and Close button",
  "keyword": "Then "
});
formatter.step({
  "line": 79,
  "name": "Get the My New Business KPI Count",
  "keyword": "And "
});
formatter.step({
  "line": 80,
  "name": "Click On My Lost Business KPI",
  "keyword": "And "
});
formatter.step({
  "line": 81,
  "name": "I search a record with the Employer Group",
  "rows": [
    {
      "cells": [
        "employergroupsearch"
      ],
      "line": 82
    },
    {
      "cells": [
        "tes1"
      ],
      "line": 83
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 84,
  "name": "I select a record under Policies tab",
  "keyword": "And "
});
formatter.step({
  "line": 85,
  "name": "Move the record to Work in Progress",
  "rows": [
    {
      "cells": [
        "MovRec3"
      ],
      "line": 86
    },
    {
      "cells": [
        "Work in Progress"
      ],
      "line": 87
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 88,
  "name": "Click on Submit and Close button",
  "keyword": "Then "
});
formatter.step({
  "line": 89,
  "name": "Get the My Lost Business KPI Count",
  "keyword": "And "
});
formatter.step({
  "line": 90,
  "name": "Click on reset button",
  "keyword": "And "
});
formatter.step({
  "line": 91,
  "name": "I select a record under Policies tab",
  "keyword": "And "
});
formatter.step({
  "line": 92,
  "name": "Move the record to YTD",
  "rows": [
    {
      "cells": [
        "MovRec4"
      ],
      "line": 93
    },
    {
      "cells": [
        "Renewal"
      ],
      "line": 94
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 95,
  "name": "Click on Submit and Close button",
  "keyword": "Then "
});
formatter.step({
  "line": 96,
  "name": "Click on YTD KPI",
  "keyword": "And "
});
formatter.step({
  "line": 97,
  "name": "I search a record with the Employer Group",
  "rows": [
    {
      "cells": [
        "employergroupsearch"
      ],
      "line": 98
    },
    {
      "cells": [
        "tes1"
      ],
      "line": 99
    }
  ],
  "keyword": "And "
});
formatter.step({
  "comments": [
    {
      "line": 100,
      "value": "###################-------Global Search-------###################"
    }
  ],
  "line": 102,
  "name": "user searches with the following global value",
  "rows": [
    {
      "cells": [
        "globalsearch"
      ],
      "line": 103
    },
    {
      "cells": [
        "tes1"
      ],
      "line": 104
    }
  ],
  "keyword": "And "
});
formatter.step({
  "comments": [
    {
      "line": 106,
      "value": "##################--------Delete Policy-------####################"
    }
  ],
  "line": 108,
  "name": "Click on Delete button",
  "keyword": "And "
});
formatter.step({
  "comments": [
    {
      "line": 110,
      "value": "##################--------Import Policy-------####################"
    }
  ],
  "line": 112,
  "name": "Click on Import Policies button",
  "keyword": "When "
});
formatter.step({
  "line": 113,
  "name": "Click on Import Submit button",
  "keyword": "Then "
});
formatter.step({
  "line": 114,
  "name": "Click alert OK button",
  "keyword": "And "
});
formatter.step({
  "comments": [
    {
      "line": 116,
      "value": "##################--------Reports-------------####################"
    }
  ],
  "line": 118,
  "name": "Click on reports button",
  "keyword": "Then "
});
formatter.step({
  "comments": [
    {
      "line": 120,
      "value": "##################--------Logout-------####################"
    }
  ],
  "line": 122,
  "name": "Click on patra corp link",
  "keyword": "When "
});
formatter.step({
  "line": 123,
  "name": "Click on Logout icon",
  "keyword": "Then "
});
formatter.step({
  "line": 124,
  "name": "Click Logout button",
  "keyword": "And "
});
formatter.match({
  "location": "MyStepdefs.iNavigateToLoginPageOfEBApp()"
});
formatter.result({
  "duration": 4636912500,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iEnterLoginCredentials(DataTable)"
});
formatter.result({
  "duration": 231369600,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iClickOnSignInButton()"
});
formatter.result({
  "duration": 41316943100,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.paginationValue(DataTable)"
});
formatter.result({
  "duration": 6214538200,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.getTheKpiCOunt()"
});
formatter.result({
  "duration": 5304406200,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnAddPolicy()"
});
formatter.result({
  "duration": 10135642100,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.enterAddPolicyDetails(DataTable)"
});
formatter.result({
  "duration": 21419262800,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnSubmitButton()"
});
formatter.result({
  "duration": 14199229900,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iSelectARecordUnderPoliciesTab()"
});
formatter.result({
  "duration": 3378872300,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.navigateToPolicyDetailsSection(DataTable)"
});
formatter.result({
  "duration": 11122255100,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnAddNoteButton()"
});
formatter.result({
  "duration": 10120653100,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.inputValuesInTheNotePopUp(DataTable)"
});
formatter.result({
  "duration": 10283450000,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.inputValuesInTheQuestionsTab(DataTable)"
});
formatter.result({
  "duration": 10374444500,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnNotesTab()"
});
formatter.result({
  "duration": 10170518000,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnHistoryTab()"
});
formatter.result({
  "duration": 10123928600,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnSubmitAndCloseButton()"
});
formatter.result({
  "duration": 15136880400,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.searchWithAllTheFiltersInTheGrid(DataTable)"
});
formatter.result({
  "duration": 12325041800,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnMyProspectsKPI()"
});
formatter.result({
  "duration": 8095285100,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iSearchARecordWithTheEmployerGroup(DataTable)"
});
formatter.result({
  "duration": 10134966600,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iSelectARecordUnderPoliciesTab()"
});
formatter.result({
  "duration": 3094841200,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iMoveTheRecordToRenewal(DataTable)"
});
formatter.result({
  "duration": 14165290900,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnSubmitAndCloseButton()"
});
formatter.result({
  "duration": 15100078300,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.getTheMyProspectsKPICount()"
});
formatter.result({
  "duration": 10034660800,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnMyRenewalsKPI()"
});
formatter.result({
  "duration": 4130712400,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iSearchARecordWithTheEmployerGroup(DataTable)"
});
formatter.result({
  "duration": 10139240100,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iSelectARecordUnderPoliciesTab()"
});
formatter.result({
  "duration": 3044874500,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iMoveTheRecordToNewBusiness(DataTable)"
});
formatter.result({
  "duration": 14196219600,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnSubmitAndCloseButton()"
});
formatter.result({
  "duration": 15126509300,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.getTheMyRenewalsKPICount()"
});
formatter.result({
  "duration": 10075001600,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnMyNewBusinessKPI()"
});
formatter.result({
  "duration": 4152007900,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iSearchARecordWithTheEmployerGroup(DataTable)"
});
formatter.result({
  "duration": 10125952900,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iSelectARecordUnderPoliciesTab()"
});
formatter.result({
  "duration": 3080229200,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.moveTheRecordToLostBusiness(DataTable)"
});
formatter.result({
  "duration": 14214634900,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnSubmitAndCloseButton()"
});
formatter.result({
  "duration": 15112842100,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.getTheMyNewBusinessKPICount()"
});
formatter.result({
  "duration": 10057180400,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnMyLostBusinessKPI()"
});
formatter.result({
  "duration": 2097984300,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iSearchARecordWithTheEmployerGroup(DataTable)"
});
formatter.result({
  "duration": 10151648600,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iSelectARecordUnderPoliciesTab()"
});
formatter.result({
  "duration": 3063328100,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.moveTheRecordToWorkInProgress(DataTable)"
});
formatter.result({
  "duration": 14107896700,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnSubmitAndCloseButton()"
});
formatter.result({
  "duration": 15089763200,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.getTheMyLostBusinessKPICount()"
});
formatter.result({
  "duration": 10058537200,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnResetButton()"
});
formatter.result({
  "duration": 10176365600,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iSelectARecordUnderPoliciesTab()"
});
formatter.result({
  "duration": 3072295300,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.moveTheRecordToYTD(DataTable)"
});
formatter.result({
  "duration": 7175368900,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnSubmitAndCloseButton()"
});
formatter.result({
  "duration": 15087884400,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnYTDKPI()"
});
formatter.result({
  "duration": 4164688000,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.iSearchARecordWithTheEmployerGroup(DataTable)"
});
formatter.result({
  "duration": 10131842100,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.userSearchesWithTheFollowingGlobalValue(DataTable)"
});
formatter.result({
  "duration": 10171811200,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnDeleteButton()"
});
formatter.result({
  "duration": 5221357000,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnImportPoliciesButton()"
});
formatter.result({
  "duration": 15143318000,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnImportSubmitButton()"
});
formatter.result({
  "duration": 2088017500,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickAlertOKButton()"
});
formatter.result({
  "duration": 10198220900,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnReportsButton()"
});
formatter.result({
  "duration": 9496726600,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnPatraCorpLink()"
});
formatter.result({
  "duration": 5643264000,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnLogoutIcon()"
});
formatter.result({
  "duration": 79517000,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickLogoutButton()"
});
formatter.result({
  "duration": 2683725200,
  "status": "passed"
});
formatter.after({
  "duration": 334883400,
  "status": "passed"
});
});