
# PaginationResponse


## Properties

Name | Type
------------ | -------------
`totalRecords` | number
`totalPages` | number
`previousPage` | number
`currentPage` | number
`nextPage` | number

## Example

```typescript
import type { PaginationResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "totalRecords": null,
  "totalPages": null,
  "previousPage": null,
  "currentPage": null,
  "nextPage": null,
} satisfies PaginationResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as PaginationResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)
