
# ErrorMessageEntry


## Properties

Name | Type
------------ | -------------
`status` | number
`title` | string
`detail` | string
`cause` | string
`meta` | [ErrorMessageMeta](ErrorMessageMeta.md)

## Example

```typescript
import type { ErrorMessageEntry } from ''

// TODO: Update the object below with actual values
const example = {
  "status": null,
  "title": null,
  "detail": null,
  "cause": null,
  "meta": null,
} satisfies ErrorMessageEntry

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as ErrorMessageEntry
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)
