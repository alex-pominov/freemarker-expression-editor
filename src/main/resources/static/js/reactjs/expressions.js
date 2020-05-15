const e = React.createElement;

const expressionsBar = () => {
  const [references, setReferences] = React.useState(null);
  console.log(references);

  // Fetch references from server
  React.useEffect(() => {
    fetch("http://localhost:8080/freemarkerReferences")
      .then((responce) => responce.json())
      .then((refs) => setReferences(refs.flat()));
  }, []);

  const onModalOpenHandler = (group, itemName) => {
    const obj = {...references.filter((item) => (item.groupName === group  || item.parentPath === group)
        && item.name === itemName)[0]};
    return openModalWithReference(obj);
  };

  const expressions = (object) => {
    if (object) {
      // Unique types of expressions
      const groups = [...new Set(Object.values(object).map((v) => v.groupName))];

      // Render accordion for each of type
      return groups.map((group) => {
        const subcategories = [
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
        // to avoid naming error during render accodrion
        const typeWithoutSpace = group.replace(/ /g, "");

        return renderAccordion(
          "expressionsTypes",
          options,
          subcategories,
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
    <div className="card">
      <div
        className={`card-header expressions--${dataParent}`}
        id={`heading${typeWithoutSpace}`}
      >
        <h2 className="mb-0" group={group}>
          <button
            className={`button collapsed ${typeWithoutSpace}`}
            type="button"
            data-toggle="collapse"
            data-target={`#${typeWithoutSpace}`}
            aria-expanded={false}
            aria-controls={`${typeWithoutSpace}`}
          >
            {group}
          </button>
          {dataParent === "categories" && <i class="arrow"></i>}
        </h2>
      </div>

      <div
        id={`${typeWithoutSpace}`}
        className="collapse"
        aria-labelledby={`heading${typeWithoutSpace}`}
        data-parent={`#${dataParent}`}
      >
        <div className={`card-body expressions--${dataParent}__menu`}>
          <ul className="nav flex-column">
            {renderAccordionOptions(group, options, categories)}
          </ul>
        </div>
      </div>
    </div>
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
      const opts = options.filter(option => !categories.includes(option));
      // if type has subcategories than render it as accordion
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
          <div class="accordion" id="categories">
            {categories.map((group) =>
              renderAccordion(
                "categories",
                (options = Object.values(references)
                  .filter((v) => v.parentPath === group)
                  .map((item) => item.name)),
                [],
                group,
                group.replace(/ /g, "")
              )
            )}
          </div>
        </React.Fragment>
      );
    }
  };

  return (
    <React.Fragment>
      <h3 className="expressions--header">Expressions</h3>
      <div class="accordion" id="expressionsTypes">
        {expressions(references)}
      </div>
    </React.Fragment>
  );
};

const domContainer = document.querySelector("#expression-container");
ReactDOM.render(e(expressionsBar), domContainer);
