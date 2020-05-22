const e = React.createElement;
const {Accordion, Card, useAccordionToggle} = ReactBootstrap;

const expressionsBar = () => {
  const [references, setReferences] = React.useState(null);

  // Fetch references from server
  React.useEffect(() => {
    fetch("http://localhost:8080/freemarkerReferences")
      .then((responce) => responce.json())
      .then((refs) => setReferences(refs.filter(Boolean).flat()));
  }, []);

  const onModalOpenHandler = (group, itemName) => {
    const obj = {
      ...references.filter((item) => (item.groupName === group || item.parentPath === group)
        && item.name === itemName)[0]
    };
    return openModalWithReference(obj);
  };

  const expressions = (object) => {
    if (object) {
      // Unique types of expressions
      const groups = [...new Set(Object.values(object).map((v) => v.groupName))].filter(v => v !== "");

      // Render accordion for each of type
      return groups.map((group) => {
        const categories = [
          ...new Set(
            Object.values(object)
              .filter((v) => v.groupName === group)
              .map((v) => v.parentPath)
              .filter(Boolean)
          ),
        ];

        let options = [
          ...new Set(
            Object.values(object)
              .filter((v) => v.groupName === group && !v.parentPath)
              .map((v) => v.name)
          ),
        ];

        // Create new variable with type without space
        // to avoid naming error during render accordion
        const typeWithoutSpace = group.replace(/ /g, "");

        return renderAccordion(
          "expressionsTypes",
          options,
          categories,
          group,
          typeWithoutSpace
        );
      });
    }
  };

  const renderAccordion = (
    dataParent,
    options,
    categories,
    group,
    typeWithoutSpace
  ) => (
    <Card>
      <Card.Header className={`expressions--${dataParent}`}>
        <CustomToggle
          selector={typeWithoutSpace}
          eventKey={typeWithoutSpace}
        >
          {group}
        </CustomToggle>
        <i className="arrow" />
      </Card.Header>
      <Accordion.Collapse eventKey={typeWithoutSpace} className="collapsed">
        <Card.Body className={`expressions--${dataParent}__menu`}>
          <ul className="nav flex-column">
            {renderAccordionOptions(group, options, categories)}
          </ul>
        </Card.Body>
      </Accordion.Collapse>
    </Card>
  );

  const renderAccordionOptions = (group, options, categories) => {
    if (!categories.length) {
      // if no subcategories than render as a list
      return options.map((item) => (
        <li className="nav-item row m-0 justify-content-between" group={group + '.' + item}>
          <button type="button" onClick={() => onModalOpenHandler(group, item)}>
            {item}
          </button>
          <span class="isVariableUsed">used</span>
        </li>
      ));
    } else {
      // if type has subcategories than render it as accordion
      const opts = options.filter(option => !categories.includes(option));
      return (
        <React.Fragment>
          {opts.length !== 0 && opts.map((item) => (
            <li className="nav-item row m-0 justify-content-between" group={group + '.' + item}>
              <button type="button" onClick={() => onModalOpenHandler(group, item)}>
                {item}
              </button>
              <span class="isVariableUsed">used</span>
            </li>
          ))}
          <Accordion>
            {categories.map((group) =>
              renderAccordion(
                "categories",
                (options = Object.values(references)
                  .filter((v) => v.parentPath === group)
                  .map((item) => item.name)),
                [
                  ...new Set(
                    Object.values(references)
                      .filter((v) => v.subcategory === group)
                      .map((v) => v.parentPath)
                      .filter(Boolean)
                  ),
                ],
                group,
                group.replace(/ /g, "")
              )
            )}
          </Accordion>
        </React.Fragment>
      );
    }
  };

  return (
    <React.Fragment>
      <h3 className="expressions--header">Expressions</h3>
      <Accordion>
        {expressions(references)}
      </Accordion>
    </React.Fragment>
  );
};

let activeKeys = [];
function CustomToggle({children, eventKey, selector}) {
  let isOpen = activeKeys.includes(eventKey);
  const toggleAccordion = useAccordionToggle(eventKey, () => {
    activeKeys = isOpen ? "" : eventKey;
  });
  return (
    <button
      type="button"
      className={`button ${selector} ${isOpen ? "expanded" : ""}`}
      // style={isOpen ? {color: '#EF7A57'} : null}
      onClick={toggleAccordion}
    >
      {children}
    </button>
  );
}

const domContainer = document.querySelector("#expression-container");
ReactDOM.render(e(expressionsBar), domContainer);
